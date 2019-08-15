var productDto = {address:"上海  杨浦  提货点A",swNo:"TWLY150401001",orderNo:"HT00004",batchNo:5,
                  productNo:"1234570",pack:"S",recNum:100,actNum:200}

var Scan = React.createClass({
	getInitialState : function(){
		//放入state变量
		return {
			showProductInfo:false,
			showCodeImage:false
		}
	},
	popup : function(){
		mui('.mui-popover').popover("hide", document.getElementById(this.props.station));
		mui('.mui-popover').popover("show", document.getElementById(this.props.station));
		var uip = document.getElementById("popover"); //topPopover是popover 的最外层div
	    uip.style.position = "absolute";
	    uip.style.left = "5%";
	},
	scanCode : function(){
		var self = this;
		//setState是异步方法，在setState执行完毕，组件重新渲染后，执行callback方法
		//componentDidUpdate，componentWillUpdate componentWillReceiveProps会重复执行
		this.setState({showProductInfo:false,showCodeImage:true}, function(){
			self.popup();
		});
	},
	showInfo : function(){
		var self = this;
		this.setState({showProductInfo:true,showCodeImage:false}, function(){
			//self.popup();
			console.info(this.state.showProductInfo);
			console.info(this.state.showCodeImage);
			//self.forceUpdate();
		});
	},
	render : function(){
		//return只能有根只能有一个div，style写法：style={{opacity: this.state.opacity}}
		//如果条码是发货信息，div中的内容显示应收件数、实收件数、收货，如果条码是收货信息，div中的内容显示应发件数、实发件数、发货
		return (
			<div id="scanBtn" className="tab-center" onClick={this.scanCode}>
				<div className="circle">
					<span className="mui-icon-extra mui-icon-extra-sweep mtop"></span>
				</div>
				
				<div id="popover" className="mui-popover pleft">
					{this.state.showCodeImage &&
					<p className="mui-text-center ptop" onClick={this.showInfo}>
						<img src="../../img/productCode.png" className="codeImage"/>
					</p>}
					{this.state.showProductInfo && <div className="productInfo">
						<p><h4>{productDto.address}</h4></p>
						<p>委托单号：{productDto.swNo}</p>	
						<p>订单号：{productDto.orderNo}</p>
						<p>批次：{productDto.batchNo}</p>
						<p>零件：{productDto.productNo}</p>
						<p>包装：{productDto.pack}</p>
						<p>应收件数：{productDto.recNum}</p>
						<p>实收件数：
						<span className="mui-numbox widthNum">
					  		<button className="mui-btn mui-numbox-btn-minus" type="button">-</button>
					  		<input className="mui-numbox-input" type="number" value="{productDto.actNum}" />
					  		<button className="mui-btn mui-numbox-btn-plus" type="button">+</button>
						</span>
						</p>
						<p className="mui-text-center">
							<button type="button" className="mui-btn mui-btn-primary productClose mright" >关闭</button>
							<button type="button" className="mui-btn mui-btn-primary productOK">收货</button>
						</p>
					</div>}
				</div>
			</div>
		)
	}
});

