<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<style type="text/css">@import url("common.css");
	#nav {
		/* 메뉴 전체 영역에 대한 스타일 */
		list-style-type: none; /* 불릿 기호 없앰 */
		height: 45px; /* 내비게이션 높이 */
		padding: 6px 7px; /* 위아래 6px, 좌우 7px씩 안여백 */
		margin: 0; /* 바깥 여백 없음 */
		background: #5CD1E5; /* 내비게이션 전체 배경색 */
		border-radius: 0.4em; /* 모서리 둥글게 */
	}
	#nav li {/* 메뉴 각 항목에 대한 스타일 */
		float: left; /* 왼쪽부터 표시 */
		position: relative; /* 순서대로 표시 */
		margin: 5px 10px; /* 위아래 5px, 좌우 10px씩 바깥 여백 */
		padding: 0; /* 안여백 없음 */
	}
	#nav li a {/* 메뉴 항목에서도 링크에 대한 스타일 */
		display: block; /* 영역을 만듦 */
		font-family: "돋움"; /* 글꼴 */
		font-weight: 600; /* 진하게 */
		font-size: 1em;	/* 글자크기 */
		padding: 10px 20px; /* 안여백 */
		color: #e7e5e5; /* 링크 글자색 */
		text-decoration: none; /* 밑줄 없앰 */
		margin: 0; /* 바깥 여백 없음 */
		border-radius: 1.4em; /* 링크의 모서리를 둥글게 */
		text-shadow: 0 1px 1px rgba(0,0,0,.3); /* 텍스트 그림자 */
		}
		#nav li:hover > a {/* 메뉴 항목 위로 마우스 올렸을 때 스타일 */
		background: #ebebeb; /* 배경색 */
		color: #444; /* 글자색 */
		text-shadow: 0 1px 1px rgba(255,255,255,1); /* 글자 그림자 */
		border-radius: 1.4em; /* 모서리 둥글게 */
		-webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.2); /* 메뉴 항목에 그림자 */
		-moz-box-shadow: 0 1px 1px rgba(0,0,0,0.2); /* 메뉴 항목에 그림자 */
	}
	#nav ul {/* 서브 메뉴 영역에 대한 스타일 */
		margin: 0; /* 서브 메뉴 바깥 여백 없음 */
		padding: 0; /* 서브 메뉴 안여백 없음 */
		list-style: none; /* 목록의 불릿 없앰 */
		position: absolute; /* 고정 위치에 표시 */
		left: 0; /* 부모 요소와 left 좌표 값은 같게 */
		top: 45px; /* 부모 요소로부터 아래로 45px 밑에 표시 */
		width: 120%; /* 서브 메뉴 너비 */
		background: #fff; /* 서브 메뉴 배경색 */
		border: 1px solid #b4b4b4; /* 서브 메뉴 테두리를 그림 */
		border-radius: 8px; /* 모서리를 둥글게 처리 */
		-webkit-box-shadow: 0 1px 3px rgba(0,0,0,.3); /* 서브 메뉴 전체에 그림자 효과 추가 */
		-moz-box-shadow: 0 1px 3px rgba(0,0,0,.3);
		box-shadow: 0 1px 3px rgba(0,0,0,.3);
		opacity: 0; /* 투명하게 표시 - 결국 평소엔 안보임 */
	}
	#nav li:hover ul {/* 메인 메뉴 위로 마우스 오버했을 때 서브 메뉴 스타일 */
			opacity: 1; /* 서브 메뉴 불투명해짐 -> 화면에 보임 */
	}
	#nav ul li {/* 서브 메뉴 각 항목의 스타일 */
		float: none; /* 세로로 표시 */
		margin: 0; /* 바깥 여백 없음 */
		padding: 0; /* 안 여백 없음 */
	}
	#nav ul a {/* 서브 메뉴 항목의 링크 스타일 */
		font-weight: normal; /* 진하기 보통으로 */
		text-shadow: 0 1px 0 #fff; /* 글자 그림자 */
		color: #333; /* 글자색 */
	}
	#nav ul li:hover  a {/* 서브 메뉴 항목 위로 마우스 오버했을 때의 스타일 */
		background: #0078ff; /* 배경색 */
		color: #fff; /* 글자색 */
		text-shadow: 0 1px 1px rgba(0,0,0,0.1); /* 글자 그림자 */
		border-radius: 0; /* 모서리 둥글게 처리 안함 */
	}
	
	#nav ul li:first-child > a {/* 서브 메뉴 첫번째 항목의 스타일 */
		-webkit-border-top-left-radius: 9px; /* 왼쪽 윗부분 둥글게 */
		-moz-border-radius-topleft: 9x; /* 오른쪽 윗부분 둥글게 */
		-webkit-border-top-right-radius: 9px;
		-moz-border-radius-topright: 9x;
	}

	#nav ul li:last-child > a {/* 서브 메뉴 마지막 항목의 스타일 */
		-webkit-border-bottom-left-radius: 9px; /* 왼쪽 아랫부분 둥글게 */
		-moz-border-radius-bottomleft: 9x; /* 오른쪽 아랫부분 둥글게 */
		-webkit-border-bottom-right-radius: 9px;
		-moz-border-radius-bottomright: 9px;
	}
	span.a1 {  font-size:40pt; font-family: sans-serif;}
</style><meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="id" value="${sessionScope.id}"></c:set>
<c:if test="${empty id }">
	<div style="text-align:right"><a href="loginForm.ao">로그인</a>&nbsp;&nbsp;&nbsp;<a href="joinForm.ao">회원가입</a></div>
</c:if>
<c:if test="${not empty id }">
	<div style="text-align:right"><a href="logout.ao">로그아웃</a>&nbsp;&nbsp;&nbsp;<a href="joinForm.ao">회원가입</a></div>
</c:if>
<br><br>
<a href="index.do"><img src="image/title.png" width="450"></a>&nbsp; &nbsp;&nbsp; &nbsp;
<a href="https://www.tottenhamhotspur.com/"> 
<img src="/epl/image/tot.png" hspace=50 width="70px" align="right"></a>&nbsp; &nbsp;
<a href="https://www.chelseafc.com/en">
<img src="/epl/image/chelsea.png" hspace=50 width="60px" align="right"></a>&nbsp; &nbsp;
<a href="https://www.mancity.com/">
<img src="/epl/image/mancity.png" hspace=50 width="60px" align="right"></a>&nbsp; &nbsp;
<a href="https://www.arsenal.com/">
<img src="/epl/image/arsenal.png" hspace=50 width="60px" align="right"></a>&nbsp; &nbsp;
<a href="https://www.liverpoolfc.com/">
<img src="/epl/image/liverpool.png" hspace=50 width="60px" align="right"></a><br><br>

<ul id="nav">	
	<li><a href="index.do"> HOME </a></li>
	<li><a href="#"> 일정/결과</a>
	
	    <!--  <ul><li><a href="#">이번달 매치</a></li>
		<li><a href="#">시즌 매치</a></li></ul> -->
		
	</li>
	
	<li><a href="rank/ranking.jsp">기록/순위</a></li>
	
	<li><a href="news/newsMain.jsp"> 뉴스 </a>
		
	</li>
	 <li><a href="list.do"> 게시판 </a>

	    </li>
	<li><a href="shopMain.co"> EPL스토어 </a>
		<!-- <ul><li><a href="#">유니폼</a></li>
		    <li><a href="#">축구화</a></li>
		    <li><a href="#">축구공</a></li>
		</ul> -->
	</li>
	   <!-- <li><a href="#">회원관리 </a>
	  	 <ul><li><a href="loginForm.ao">로그인</a></li>
			<li><a href="joinForm.ao">회원가입</a></li>
		       	<li><a href="#">상품 수정</a></li>
		       	<li><a href="#">상품 삭제</a></li></ul>
	    </li> -->
</ul>
</body>
</html>