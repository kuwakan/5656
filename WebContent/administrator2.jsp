<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="casestudy.Beans" %>
<%Beans t = (Beans) application.getAttribute("beans"); %>


<html>
	<head>
		<title>N1</title>
		<meta charset="utf-8" />


		<link rel="stylesheet" href="assets/css/button.css" />
		<link rel="stylesheet" href="assets/css/toggle.css" />
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
  送信する？
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
 送信する？
</button>





	   </form>


	   <%} %>
   </div>
</body>
</html>