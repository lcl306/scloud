var Scan = React.createClass({
	getInitialState : function(){
		//放入state变量
		return {
			showProductInfo:false,
			showCodeImage:false
		}
	},
	scanCode : function(){
		this.setState({showProductInfo:false,showCodeImage:true});
		mui('.mui-popover').popover("show", scanOver);
		var uip = document.getElementById("popover"); //topPopover是popover 的最外层div
	    uip.style.position = "absolute";
	    uip.style.left = "5%";
	},
	showInfo : function(){
		this.setState({showProductInfo:true,showCodeImage:false});
	},
	render : function(){
		//return只能有根只能有一个div，不能有style
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
						<p><h4>上海&nbsp;&nbsp;杨浦&nbsp;&nbsp;提货点A</h4></p>
						<p>委托单号：TWLY150401001</p>	
						<p>订单号：HT00004</p>
						<p>批次：5</p>
						<p>零件：1234570</p>
						<p>包装：S</p>
						<p>应收件数：30</p>
						<p>实收件数：
						<span className="mui-numbox widthNum">
					  		<button className="mui-btn mui-numbox-btn-minus" type="button">-</button>
					  		<input className="mui-numbox-input" type="number" />
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

