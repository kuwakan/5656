<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="casestudy.Beans" %>
<%Beans t = (Beans) application.getAttribute("beans"); %>


<html>
	<head>
		<title>N1</title>
		<meta charset="utf-8" />


	<link rel="stylesheet" href="./assets/css/button.css" />
	<link rel="stylesheet" href="./assets/css/toggle.css" />
	<link rel="stylesheet" href="./assets/css/dbbutton.css" />


	</head>
<body>



   <%if(t!=null){ %>

   <div class="toggles">
	   <form action="/connection/ConOrDie2" method="post" >

   <h1>ＤＢに格納する？</h1>
	      <div class="toggle-border">
	      <%if(t.isIn()){ %>
     <input type="checkbox" id="one" name="in" value="1" checked="checked"/>
	         <%}else if(t.isIn()!=true){ %>
	          <input type="checkbox" id="one" name="in" value="1" />
	          <%} %>
	         <label for="one">
	            <div class="handle"></div>
	         </label>
	      </div>
	      <h1>XML吐き出す？</h1>
	      <div class="toggle-border">
	      <%if(t.isXml()){ %>
	         <input type="checkbox" id="two" name="xml" value="1" checked="checked" />
	          <%}else if(t.isXml()!=true){ %>
	          <input type="checkbox" id="two" name="xml" value="1" />
	          <%} %>
	         <label for="two">
	            <div class="handle"></div>
	         </label>
	      </div>




<button>
  Are you sure?
</button>





	   </form>
	   <%}else if(t==null){ %>

   <div class="toggles">
	    <form action="/connection/ConOrDie" method="post" >

   <h1>ＤＢに格納する？</h1>
	      <div class="toggle-border">



	          <input type="checkbox" id="one" name="in" value="1" />

	         <label for="one">
	            <div class="handle"></div>
	         </label>
	      </div>
	      <h1>XML吐き出す？</h1>
	      <div class="toggle-border">


	          <input type="checkbox" id="two" name="xml" value="1" />

	         <label for="two">
	            <div class="handle"></div>
	         </label>
	      </div>




<button>
 Are you sure?
</button>





	   </form>

	   <%} %>
   </div>



<br><br><br><br><br><br>

<li><a href="#" class="round green" onclick="document.form1.submit();return false;">ADMIN END<span class="round">管理者画面を終了します</span></a>

 <form name="form1" method="POST" action="/connection/AdminEnd" >


</form>
</li>

<li><a href="#" class="round red"" onclick="document.form2.submit();return false;">DB RESET<span class="round">DBを全て消します</span></a>

 <form name="form2" method="POST" action="/connection/DBdeleteServlet" >


</form>
</li>

<li><a href="#" class="round yellow" onclick="document.form3.submit();return false;">PRESERVE DB<span class="round">DBをCSVで保存します</span></a>

 <form name="form3" method="POST" action="/connection/CsvMaker" >


</form>
</li>













</body>
</html>