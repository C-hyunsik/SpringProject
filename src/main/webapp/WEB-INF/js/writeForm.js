$(function(){
	//아이디 중복체크
	$('#id').focusout(function(){
		$('#idDiv').empty();
		id = $('#id').val();
		if(id == ''){
			$('#idDiv').html('먼저 아이디 입력');
		}
		else{
			$.ajax({
				type:'post',
				url:'/spring/user/searchUser',
				data:{'id':id},
				dataType:'text',
				success:function(data){
					if(data == 'exist'){
						$('#idDiv').html('사용 불가능');
					}
					else{
						$('#idDiv').html('<span style="color:blue;">사용 가능</span>');
					}
				},
				error:function(e){
					console.log(e);
				}
			});
		}
	});
	
	//회원가입
	$('#writeBtn').click(function(){
		$('#idDiv').empty();
		$('#nameDiv').empty();
		$('#pwdDiv').empty();
		
		id = $('#id').val();
		name = $('#name').val();
		pwd = $('#pwd').val();
		
		if(!id){
			$('#idDiv').html('아이디 입력');
		}
		else if(!name){
			$('#nameDiv').html('이름 입력');
		}
		else if(!pwd){
			$('#pwdDiv').html('비밀번호 입력');
		}
		$.ajax({
			type:'post',
			url:'/spring/user/userWrite',
			data:$('#userWriteForm').serialize(),
			success:function(){
				alert('회원가입이 완료되었습니다.')
				location.href='/spring/user/listForm';
			},
			error:function(e){
				console.log(e);
			}
		});
	});
});