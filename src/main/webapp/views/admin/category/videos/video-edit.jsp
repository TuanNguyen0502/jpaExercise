<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 10/11/2024
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<form action="${pageContext.request.contextPath}/admin/category/video/edit" method="post">
    <input type="text" id="category_id" name="categoryId" value="${video.category.id}" hidden="hidden">
    <input type="text" id="id" name="id" value="${video.id}" hidden="hidden">

    <label for="statuson">Status:</label><br>
    <input type="radio" id="statuson" name="active" value="1" ${video.active==1 ? 'checked' : ''}>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="active" value="0" ${video.active == 0 ? 'checked' : ''}>
    <label for="statusoff">Inactive</label><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" value="${video.description}"><br>

    <label for="poster">Poster:</label><br>
    <input type="text" id="poster" name="poster" value="${video.poster}"><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value="${video.views}"><br>

    <input type="submit" value="Edit">
</form>
