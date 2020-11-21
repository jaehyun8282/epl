<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="utf-8">
<title>Insert title here</title>
<style>
        /* * { margin:auto; } */
        /* Animation Canvas */
        .animation_canvas  { margin:auto;
            overflow:hidden;
            position:relative;           
            width:1000px; height:600px;
        }    
        /* Slider Panel */
        .slider_panel { width:5000px; position:relative; }
        .slider_image { float:left; width:1000px; height:600px; }    
        /* Slider Text Panel */
       /*  .slider_text_panel { position:absolute; top:200px; left:50px; }
        .slider_text { position:absolute; width:250px; height:150px; }
     */
        /* Control Panel */
        .control_panel  {
            position:absolute; top:580px; 
            left:470px; height:13px; 
            overflow:hidden; 
        }
         .control_button {
            width:12px; height:46px;
            position:relative; 
    
            float:left; cursor:pointer;  
            background:url('image/point_button.png');
        }
        .control_button:hover { top:-16px; }
        .control_button.active { top:-31px; }
        .slider_text h1 { color: white; }
    </style>
    <script src="js/jquery.js"></script>
    <script>
    	$(document).ready(function () {
            // 슬라이더를 움직여주는 함수
            function moveSlider(index) {
                // 슬라이더를 이동합니다.
                var willMoveLeft = -(index * 1000);
                $('.slider_panel').animate({ left: willMoveLeft }, 'slow');
                // control_button에 active클래스를 부여/제거합니다.
                $('.control_button[data-index=' + index + ']').addClass('active');
                $('.control_button[data-index!=' + index + ']').removeClass('active');
                // 글자를 이동합니다.
                $('.slider_text[data-index=' + index + ']').show().animate({
                    left: 0
                }, 'slow');
                $('.slider_text[data-index!=' + index + ']').hide('slow', function () {
                    $(this).css('left', -700);
                });
            }
            // 초기 텍스트 위치 지정 및 data-index 할당
            $('.slider_text').css('left', -300).each(function (index) {
                $(this).attr('data-index', index);
            });

            // 컨트롤 버튼의 클릭 핸들러 지정 및 data-index 할당
            $('.control_button').each(function (index) {
                $(this).attr('data-index', index);
            }).click(function () {
                var index = $(this).attr('data-index');
                moveSlider(index);
            });
         // 초기 슬라이더 위치 지정
         var randomNumber = 0;
           /*  var randomNumber = Math.floor(Math.random() * 5); */
            moveSlider(randomNumber);
			setInterval(() => {
			if(randomNumber < 4) {
				randomNumber++;
		      } else {
		    	  randomNumber = 0;
		      }
			/*  var randomNumber = Math.floor(Math.random() * 5);*/
            moveSlider(randomNumber);
		}, 3000);
    });
</script>       
</head>
<body background="image/bg1.jpg">
   <!--  <h1>Test Header</h1> -->
    <div class="animation_canvas">
        <div class="slider_panel">
            <img src="image/sh1.jpg" class="slider_image"/>
            <img src="image/bl2.jpg" class="slider_image"/>
            <img src="image/wat1.jpg" class="slider_image"/>
            <img src="image/new1.jpg" class="slider_image"/>
            <img src="image/cp1.jpg" class="slider_image"/>
        </div>
        <!-- <div class="slider_text_panel">
            <div class="slider_text">
                <h1>Lorem ipsum</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </div>
            <div class="slider_text">
                <h1>Nulla eget</h1>
                <p>Nulla eget sapien mauris, ornare elementum elit.</p>
            </div>
      		 <div class="slider_text">
                <h1>Quisque eleifend</h1>
                  <p>Quisque eleifend augue nec lacus lobortis porta.</p>
            </div>
            <div class="slider_text">
                <h1>Donec</h1>
                <p>Donec a ligula lectus, eu iaculis justo.</p>
            </div>
            <div class="slider_text">
                <h1>Vivamus scelerisque</h1>
                <p>Vivamus scelerisque mauris id nunc dictum sit amet.</p>
            </div>
        </div> -->
        <div class="control_panel">
            <div class="control_button"></div>
            <div class="control_button"></div>
            <div class="control_button"></div>
            <div class="control_button"></div>
            <div class="control_button"></div>
        </div>
    </div>
    <!-- <h1>Test Header</h1> -->
</body>
</html>