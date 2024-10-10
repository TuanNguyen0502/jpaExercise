<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 10/11/2024
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>

<form action="${pageContext.request.contextPath}/admin/category/video/add" method="post">
  <input type="text" id="category_id" name="categoryId" value="${categoryId}" hidden="hidden">

  <label for="id">ID:</label><br>
  <input type="text" id="id" name="id"><br>

  <p>Status:</p>
  <input type="radio" id="statuson" name="active" value="1" checked>
  <label for="statuson">Active</label><br>
  <input type="radio" id="statusoff" name="active" value="0">
  <label for="statusoff">Inactive</label><br><br>

  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description"><br>

  <label for="poster">Poster:</label><br>
  <input type="text" id="poster" name="poster"><br>

  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title"><br>

  <label for="views">Views:</label><br>
  <input type="text" id="views" name="views"><br>

  <input type="submit" value="Insert">
</form>
