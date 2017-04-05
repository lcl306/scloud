
var compressUrl = "_doc/chat/camera/";

var imagePage={
    imgUp:function(serverUrl, callback){  
        var m=this;  
        plus.nativeUI.actionSheet({cancel:"取消",buttons:[{title:"拍照"},{title:"从相册中选择"}]}, 
        function(e){//1 是拍照  2 从相册中选择
            switch(e.index){  
                case 1:clickCamera(serverUrl, callback);break;  
                case 2:clickGallery(serverUrl, callback);break;  
        	}  
    	});  
    }
}  
              
//发送照片  
function clickGallery(serverUrl, callback) {  
    plus.gallery.pick(function(path) {
    	imageUpload(plus, path, compressUrl+path, serverUrl, callback);
    }, function(err) {
    	
    });
};  
  
// 拍照  
function clickCamera(serverUrl, callback) {  
    var cmr = plus.camera.getCamera();  
    var res = cmr.supportedImageResolutions[0];  
    var fmt = cmr.supportedImageFormats[0];  
    cmr.captureImage(function(path) {  
        plus.io.resolveLocalFileSystemURL(path, function(entry) {  
            var localUrl = entry.toLocalURL();  
            imageUpload(plus, localUrl, compressUrl+localUrl, serverUrl, callback);
        });  
    }, function(err) {  
        console.error("拍照失败：" + err.message);  
    }, {index:1,resolution:res,format:fmt});  //format是文件格式，1是主摄像头  
}; 

// 压缩上传
function imageUpload(plus, srcUrl, dstUrl, serverUrl, callback){
	plus.zip.compressImage({  
        src: srcUrl,  
        dst: dstUrl,  
        quality: 20,  
        overwrite: true  
    }, function(e) {  
        var task = plus.uploader.createUpload(sUrl, {method: "post"}, function(t, sta) {  
            if(sta == 200) {  
                var msg = t.responseText;  
                if(callback) callback(msg);
                //var oImg = JSON.parse(msg);
                /*var imgUrl = oImg.urls;  
                var re = new RegExp("\\\\", "g");  
                imgUrl = imgUrl.replace(re, "/");  
                console.log(imgUrl);  
                uploadMsg(2, imgUrl);*/  
            }  
        });  
        task.addFile(e.target, {});  
        task.start();  
    }, function(err) {  
        console.log("压缩失败：  " + err.message);  
    });
}

function imageInit(plusReady){
	console.info(mui("body"));
	if(window.plus){
		plusReady();
	}else{
		document.addEventListener("plusready",plusReady,false);
	}
}