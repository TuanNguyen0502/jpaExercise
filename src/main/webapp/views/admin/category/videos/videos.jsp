<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 10/11/2024
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="<c:url value='/admin/category/video/add?id=${categoryId}'/>" class="center">Add Video</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>ID</th>
        <th>Status</th>
        <th>Description</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Views</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="video" items="${videos}" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            <td>${video.id}</td>
            <td>
                <c:if test="${video.active == 1}">
                    <span class="label label-success">Active</span>
                </c:if>
                <c:if test="${video.active == 0}">
                    <span class="label label-danger">Inactive</span>
                </c:if>
            </td>
            <td>${video.description}</td>
            <td>${video.poster}</td>
            <td>${video.title}</td>
            <td>${video.views}</td>
            <td>
                <a href="<c:url value='/admin/category/video/edit?id=${video.id}'/>" class="center">Sửa</a>
                |
                <a href="<c:url value='/admin/category/video/delete?id=${video.id}'/>" class="center">Xóa</a>
            </td>
        </tr>
    </c:forEach>

</table>

