<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--有文章種類顯示 -->
<c:if test="${totalPages == 0 }">
	<center>
		<div style="background-color:#F5DEB3; line-height:50px; font-size:35px;">此 種 類 無 文 章</div>
	</center>
</c:if>
<c:if test="${empty param.ClassNo && totalPages != 0}">
	<center>
		<div class="col-xs-12">
			<ul class="pagination">
				<c:if test="${pageNo > 1}">
					<li><a href="<c:url value='Forum.do?pageNo=${pageNo-1}' />">
							<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
						</a></li>
				</c:if>
				<li><a href="<c:url value='Forum.do?pageNo=1' />"> 1 </a></li>
				<c:if test="${3 <= totalPages and totalPages <= 5}">
					<c:forEach var="page" begin="2" end="${totalPages - 1}">
						<li><a href="<c:url value='Forum.do?pageNo=${page}' />"> ${page} </a></li>
					</c:forEach>
				</c:if>
				<c:if test="${totalPages > 5 and pageNo >= 4}">
					<li><span>...</span></li>
				</c:if>
				<c:if test="${totalPages > 5}">
					<c:choose>
						<c:when test="${1 <= pageNo and  pageNo <= 3}">
							<c:forEach var="page" begin="2" end="4">
								<li><a href="<c:url value='Forum.do?pageNo=${page}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
						<c:when test="${4 <= pageNo and  pageNo <= totalPages -3}">
							<c:forEach var="page" begin="${pageNo-2}" end="${pageNo + 2}">
								<li><a href="<c:url value='Forum.do?pageNo=${page}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
						<c:when test="${totalPages -3 <= pageNo and  pageNo <= totalPages}">
							<c:forEach var="page" begin="${totalPages -3}" end="${totalPages - 1}">
								<li><a href="<c:url value='Forum.do?pageNo=${page}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:if>
				<c:if test="${totalPages > 5 and pageNo < totalPages -2}">
					<li><span>...</span></li>
				</c:if>
				<c:if test="${totalPages > 1}">
					<li><a href="<c:url value='Forum.do?pageNo=${totalPages}' />"> ${totalPages} </a></li>
				</c:if>
				<c:if test="${pageNo != totalPages}">
					<li><a href="<c:url value='Forum.do?pageNo=${pageNo+1}' />">
							<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
						</a></li>
				</c:if>
			</ul>
		</div>
	</center>
</c:if>
<!--有文章種類顯示 -->
<!--沒有文章種類顯示 -->
<c:if test="${not empty param.ClassNo && totalPages != 0}">
	<center>
		<div class="col-xs-12">
			<ul class="pagination">
				<c:if test="${pageNo > 1}">
					<li><a href="<c:url value='Forum.do?pageNo=${pageNo-1}&ClassNo=${param.ClassNo}' />">
							<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
						</a></li>
				</c:if>
				<li><a href="<c:url value='Forum.do?pageNo=1&ClassNo=${param.ClassNo}' />"> 1 </a></li>
				<c:if test="${3 <= totalPages and totalPages <= 5}">
					<c:forEach var="page" begin="2" end="${totalPages - 1}">
						<li><a href="<c:url value='Forum.do?pageNo=${page}&ClassNo=${param.ClassNo}' />"> ${page} </a></li>
					</c:forEach>
				</c:if>
				<c:if test="${totalPages > 5 and pageNo >= 4}">
					<li><span>...</span></li>
				</c:if>
				<c:if test="${totalPages > 5}">
					<c:choose>
						<c:when test="${1 <= pageNo and  pageNo <= 3}">
							<c:forEach var="page" begin="2" end="4">
								<li><a href="<c:url value='Forum.do?pageNo=${page}&ClassNo=${param.ClassNo}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
						<c:when test="${4 <= pageNo and  pageNo <= totalPages -3}">
							<c:forEach var="page" begin="${pageNo-2}" end="${pageNo + 2}">
								<li><a href="<c:url value='Forum.do?pageNo=${page}&ClassNo=${param.ClassNo}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
						<c:when test="${totalPages -3 <= pageNo and  pageNo <= totalPages}">
							<c:forEach var="page" begin="${totalPages -3}" end="${totalPages - 1}">
								<li><a href="<c:url value='Forum.do?pageNo=${page}&ClassNo=${param.ClassNo}' />"> ${page} </a></li>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:if>
				<c:if test="${totalPages > 5 and pageNo < totalPages -2}">
					<li><span>...</span></li>
				</c:if>
				<c:if test="${totalPages > 1}">
					<li><a href="<c:url value='Forum.do?pageNo=${totalPages}&ClassNo=${param.ClassNo}' />"> ${totalPages} </a></li>
				</c:if>
				<c:if test="${pageNo != totalPages}">
					<li><a href="<c:url value='Forum.do?pageNo=${pageNo+1}&ClassNo=${param.ClassNo}' />">
							<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
						</a></li>
				</c:if>
			</ul>
		</div>
	</center>
</c:if>
<!--沒有文章種類顯示 -->