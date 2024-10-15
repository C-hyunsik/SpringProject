$('#writeBtn').click(function(){
	$('#idDiv').empty();
	$('#nameDiv').empty();
	$('#pwdDiv').empty();
	
	id = $('#id').val();
	name = $('#name').val();
	pwd = $('#pwd').val();
	pg = $('#pg').val();
	
	if(!name){
		$('#nameDiv').html('먼저 이름을 입력');
	}
	else if(!pwd){
		$('#pwdDiv').html('먼저 비밀번호를 입력');
	}
	else{
		$.ajax({
			type:'post',
			url:'/spring/user/userUpdate',
			data:$('#userUpdateForm').serialize(),
			success:function(){
				alert('정보를 수정하였습니다.');
				location.href = '/spring/user/listForm?pg='+pg;
			},
			error:function(e){
				console.log(e);
			}
		});
	}
});