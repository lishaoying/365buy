<!DOCTYPE HTML>
<html>
	<head>
	</head>
	<link rel='stylesheet' href='show.css' type='text/css'>
	
	<body onload="newObject(1)"><br>
	
		<!--准备-->
		<jsp:include page="menu.jsp"></jsp:include>
		<%@ page contentType="text/html;charset=gb2312"%>
	<table id="t1" border="1" width="100%">
    <script>
        for(row=1;row<=2;row++){
            document.write("<tr align='center'>");
            for(cell=1;cell<=4;cell++){
               document.write("<td></td>");
            }
            document.write("</tr>");
        }
    </script>
    <script src="products.js">
    </script>
</table>
<script>
     var pages=Math.ceil(pageCount/8);
    for(i=0;i<pages;i++){
    var str='<a href="javascript:newObject('+(i+1)+')">'+(i+1)+'</a>';
    document.write(str);
    
    }
    </script>

	</body>
</html>
