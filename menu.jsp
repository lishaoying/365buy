<%@ page contentType="text/html;charset=gb2312"%>
<html>
<style type="text/css">
    *{margin:0;padding:0;}
    #title{
        <!--background-image: url("img/1.png");-->
        position:absolute;top:0;left:0;
        width: 103%;height:25px;
        border:1px solid lightblue;
    }
    #item{
        position:absolute;
        left:300px;
    }
    ul{
        width:800px;
        font-size: 12px;
    }
    li{
        list-style-type: none;
        float:left;
        margin:4px 10px;
    }
    .plac{
        border-left: 1px solid #CCCCCC;
    }
</style>
<body>
<div id="title">
    <div id="item">
        <ul>
             <%
               Cookie cs[]=request.getCookies();
               String user="";
               try{
               for(Cookie c:cs){
                  if(c.getName().equals("user")){
                     user=c.getValue();
                  }
               }
               }catch(Exception e){
               }
               if(!user.equals("")){
              %>
                  <li>你好，<%=user%>!</li>
              <%
               }else{
                user=(String)session.getAttribute("user");
                if(user==null){%>
                   <li>您好！欢迎来到京东商城！</li>
                <%}else{%>
                <li>你好，<%=user%>!</li>
                <%
                } %>
                <%
                }
                 %>           
            <li><a href="login.jsp">[登录]</a></li>
            <li><a href="reg.html">[免费注册]</a></li>
            <li class="plac"></li>
            <li>我的订单</li>
            <li class="plac"></li>
            <li>特色栏目</li>
            <li class="plac"></li>
            <li>移动京东</li>
            <li id="plac"></li>
            <li>企业服务</li>
            <li id="plac"></li>
            <li>客户服务</li>
        </ul>
    </div>
</div>
</body>
</html>