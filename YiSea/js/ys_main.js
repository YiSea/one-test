$(document).ready(function(){
	//alert("Happy  ~^_^~ ");

});
		
function hello(){
	var i = parseInt(Math.random()*7);
	//alert(i);
	switch(i){
		case 1:{
			$("#yisea").val("");
			$("#yisea").val("Happy  ~^_^~ ");
			//alert("Happy  ~^_^~ ");
			break;
		}
		case 2:{
			$("#yisea").val("");
			$("#yisea").val("���Ҹ���  ~^_^~ ");
			//alert("���Ҹ���  ~^_^~ ");
			break;
		}
		case 3:{
			$("#yisea").val("");
			$("#yisea").val("�ֵ���  ~^_^~ ");				
			//alert("�ֵ���  ~^_^~ ");
			break;
		}
		case 4:{
			$("#yisea").val("");
			$("#yisea").val("���ǲ���ϲ���� ~^_^~ ���ҵĿۿ��ǣ�519814437��������Ѿ ");				
			//alert("���ǲ���ϲ���� ~^_^~ ���ҵĿۿ��ǣ�519814437��������Ѿ");
			break;
		}
		case 5:{
			$("#yisea").val("");
			$("#yisea").val("����Smart Boy  ~^_^~ ");				
			//alert("����ɵ��  ~^_^~ ");
			break;
		}
		default:{	
			$("#yisea").val("");
			$("#yisea").val("����������~");
			//alert("����������~");
		
		}
		
	}
}

function showLable(){
 	var discss = $("#add_url").css("display");
 	if(discss == 'none'){
 		$("#add_url").css("display","block");
		$("#borm").css("display","none");
		$("#addMenu").val("-");
 	}else{
		$("#add_url").css("display","none");
		$("#borm").css("display","block");
		$("#addMenu").val("+");
	}
}

function addLable(){
	var obj = $("#yisea");
	var imagName = $("#imag_name").val();
	var imagUrl = $("#imag_url").val();
	if(imagName == "" || imagUrl == ""){
 		obj.val("ͼƬ�����ֺ�url����Ϊ����~~��_��");
		return;
	}else{
		var html = "&nbsp;<a href=" + "'http://" + imagUrl + "/'" +  " target='_blank'><img src='images/" + imagName + ".png'" + " /></a>";
		alert("html::" + html);
		$("#window").html(html);
	}
	
}

function go(){
	var x = $("#yisea").val();
 	if(x.trim() == ""){
 		return;
	}else{
		var url = "https://www.baidu.com/s?wd=" + x;
		window.open(url);
	}
}

function HappyGo(){
	var url = $('#happy').val();
	//alert("url:" + url);
	window.open="http://" + url +"?target=_black";
}



