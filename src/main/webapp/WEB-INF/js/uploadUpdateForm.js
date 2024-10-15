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
	$('#updateBtn').click(function(){
		let formData = new FormData($('#updateAJaxForm')[0]);
		
		img = $('#img').val();
		existFile = $('#existFile').val();
		seq = $('#seq').val();
		if(!img){
			formData.append('img',existFile);
		}
		$.ajax({
			type:'post',
			enctype:'multipart/form-data',
			processData: false,
			contentType: false,
			url:'/spring/user/ncpUpdate',
			data: formData,
			success: function(data){
				alert('글을 수정하였습니다.');
				location.href='/spring/user/uploadView?seq=' + seq;
			},
			error: function(e){
				console.log(e);
			}
		});//ajax
	});
});