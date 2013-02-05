function increase_width(tag)
{
	document.getElementById('file1_div').style.width="15%";
	document.getElementById('file2_div').style.width="15%";
	//document.getElementById('ajetias_css_div').style.width="15%";
	tag.parentNode.style.width="75%";
}

function decrease_width()
{
	document.getElementById('file1_div').style.width="45%";
	document.getElementById('file2_div').style.width="45%";
	//document.getElementById('ajetias_css_div').style.width="30%";
}

function ajaxFunc()
{
  var input1= document.getElementById("file1.div").value;
  var splitInput1=input1.split("\n");
  var jsonInput1=JSON.stringify(splitInput1);
  //alert(jsonInput1);
  
  var input2= document.getElementById("file2.div").value;
  var splitInput2=input2.split("\n");
  var jsonInput2=JSON.stringify(splitInput2);
  //alert(jsonInput2);
  
  createXMLHTTP(jsonInput1,jsonInput2);
 }
 
 function createXMLHTTP(jsonInput1,jsonInput2)
 {
  var xmlhttp;
  if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
  } else{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
	if (xmlhttp.readyState==4) {
		document.getElementById("output").innerHTML=xmlhttp.responseText;
    } 
  };
  xmlhttp.open("POST","http://localhost:8080/embed/TryThis",true);
  //xmlhttp.open("POST","DiffServlet",true);
  xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xmlhttp.send("input1=" + jsonInput1 + "&input2=" + jsonInput2);
}
