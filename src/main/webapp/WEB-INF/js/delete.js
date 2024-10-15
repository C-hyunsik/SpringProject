$('#deleteBtn').click(function(){
	checkPwd = $('#checkPwd').val();
	pwd = $('#pwd').val();
	if(checkPwd != pwd){
		$('#pwdDiv').html('비밀번호가 일치하지 않습니다.');
	}
	else{
		id = $('#id').val();
		$.ajax({
			type:'post',
			url:'/spring/user/userDelete',
			data:{'id':id},
			success:function(){
				alert('정보를 삭제하였습니다.');
				location.href='/spring/user/listForm';
			},
			error:function(e){
				console.log(e);
			}
		});
	}
});