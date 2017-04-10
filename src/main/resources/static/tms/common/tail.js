// 引用方式为text/jsx <script type="text/jsx" src="../common/tail.js" ></script>
// class要变为className
var Tail = React.createClass({
	// 在第一次渲染后调用，只在客户端。之后组件已经生成了对应的DOM结构，可以通过this.getDOMNode()来进行访问
	componentDidMount: function () {
		//如果你想和其他JavaScript框架一起使用，可以在这个方法中调用setTimeout, setInterval或者发送AJAX请求等操作(防止异部操作阻塞UI)。
	    this.timer = setTimeout(function () {
	    	this.refs[this.props.active].className="mui-tab-item mui-active";
	    }.bind(this), 100);
	},
	go : function(url, event){
		document.location.href=url;
	},
	render : function(){
		return (
			<nav className="mui-bar mui-bar-tab">
				<a id="mainNav" ref="mainNav" className="mui-tab-item" onClick={this.go.bind(this, 'main.html')} href="#">
					<span className="mui-tab-label">首页</span>
				</a>
				<a id="mapNav" ref="mapNav" className="mui-tab-item" onClick={this.go.bind(this, 'tracing.html')} href="#">
					<span className="mui-tab-label">地图</span>
				</a>
				{this.props.showAppend=="true" && <a id="appendNav" ref="appendNav" className="mui-tab-item" href="javascript:void(0);"></a>}
				{this.props.showAb=="true" &&
				<a id="abNav" ref="abNav" className="mui-tab-item" onClick={this.go.bind(this, 'abnormal.html')} href="#">
					<span className="mui-tab-label">异常</span>
				</a>
				}
				<a id="outNav" className="mui-tab-item" onClick={this.go.bind(this, '../../index.html')} href="#">
					<span className="mui-tab-label">退出</span>
				</a>
			</nav>
		)
	}
});
