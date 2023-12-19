<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 목록</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	margin: 20px;
}

h2 {
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: left;
	border: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:hover {
	background-color: #f5f5f5;
}

form {
	margin-top: 20px;
	display: flex;
	flex-direction: column;
	max-width: 400px;
}

input[type="text"], textarea {
	margin-bottom: 10px;
	padding: 8px;
	font-size: 16px;
}

input[type="submit"], input[type="button"] {
	margin-top: 10px;
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px;
	font-size: 18px;
	cursor: pointer;
}

input[type="submit"]:hover, input[type="button"]:hover {
	background-color: #45a049;
}

/* 추가된 스타일 */
#editForm {
	width: 400px;
	height: 450px;
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	z-index: 1000;
}
</style>
</head>
<script>
window.onload = function () {
    var msg = '${msg}';
    if (msg !== null && msg !== '') {
        alert(msg);
    }
}
</script>
<body>

	<div>
		<h2>전체 회원 목록</h2>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<!-- 회원 목록을 반복해서 출력 -->
				<c:forEach var="list" items="${lists}">
					<tr
						onclick="showEditForm('${list.bno}', '${list.title}', '${list.content}', '${list.writer}')">
						<td>${list.bno}</td>
						<td>${list.title}</td>
						<td>${list.content}</td>
						<td>${list.writer}</td>
						<td>${list.regdate}</td>
						<td>${list.viewcnt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<form action="<%=contextPath%>/board/insert" method="post"
		enctype="utf-8">
		<h2>게시물 작성</h2>
		<input type="text" name="title" placeholder="제목">
		<textarea name="content" placeholder="내용" rows="5"></textarea>
		<input type="text" name="writer" placeholder="작성자"> <input
			type="submit" value="등록">
	</form>

	<div id="editForm">
		<h2>게시물 수정</h2>
		<form action="<%=contextPath%>/board/update" method="post"
			enctype="utf-8">
			<input type="hidden" id="editBno" name="bno"> <input
				type="hidden" id="editWriter" name="writer"> <input
				type="text" id="editTitle" name="title" placeholder="제목">
			<textarea id="editContent" name="content" placeholder="내용" rows="5"></textarea>
			<input type="submit" value="수정"> <input type="button"
				value="삭제" onclick="confirmDelete()"> <input type="button"
				value="닫기" onclick="closeEditForm()">
		</form>
	</div>

	<script>
	function showEditForm(bno, title, content, writer) {
        document.getElementById('editBno').value = bno;
        document.getElementById('editWriter').value = writer;
        document.getElementById('editTitle').value = title;
        document.getElementById('editContent').value = content;
        document.getElementById('editForm').style.display = 'block';
        updateViewCnt(bno);
    }

    function updateViewCnt(bno) {
        // AJAX를 사용하여 서버에 조회수 증가 요청을 보냄
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '<%=contextPath%>/board/updateViewCnt?bno=' + bno, true);
        xhr.send();
    }

    function confirmDelete() {
        var result = confirm("게시물을 삭제하시겠습니까?");
        if (result) {
            window.location.href = "<%=contextPath%>/board/delete?bno=" + document.getElementById('editBno').value;
            alert("삭제되었습니다.");
        }
    }

    function closeEditForm() {
        document.getElementById('editForm').style.display = 'none';
        window.location.href = "<%=contextPath%>/board/list";
    }
</script>
</body>
</html>
