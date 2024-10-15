$('#camera').click(function(){
	$('#img').trigger('click');
});
//이미지 미리보기
$('#img').change(function(){
	$('#showImageList').empty();
	
	for(var i = 0; i < this.files.length; i++){
		readURL(this.files[i]);
	}
});
function readURL(file){
	var reader = new FileReader();
	
	var show;
	reader.onload = function(e){
		var img = document.createElement('img');	//<img> 태그 만들기
		img.src= e.target.result;
		img.width = 70;
		img.height = 70;
		$('#showImageList').append(img);
	}
	reader.readAsDataURL(file);
}

$(function(){
	$('#uploadBtn').click(function(){
		img = $('#img').val();
		if(!img){
			alert('파일을 선택해주세요');
		}
		else{
			let formData = new FormData($('#uploadAJaxForm')[0]);
			$.ajax({
				type:'post',
				enctype:'multipart/form-data',
				processData: false,
				contentType: false,
				url:'/spring/user/ncpUpload',
				data: formData,
				success: function(data){
					alert('이미지를 등록하였습니다.');
					location.href='/spring/user/uploadList';
				},
				error: function(e){
					console.log(e);
				}
			});//ajax
		}
	});
});
/*
processData
 - 기본값은 true
 - 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
 - 파일 전송시에는 반드시 false로 해야 한다.(formData를 문자열로 변환하지 않는다)
 
contentType
  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
*/