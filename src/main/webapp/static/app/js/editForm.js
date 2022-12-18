let images = [];

const editForm = {
    
    init: function () {
        const _this = this;
        $('.btn-delete-file').on('click', function (e) {
            _this.delete_file(e);
        })
    },
	
    delete_file : function (e) {
		e.target.parentNode.remove();
	
		images.push(e.target.value);
    },
    
    
    update : function() {
	
		const title = $('#title').val();
     	   
        const content = $('#content').val();
        
     	const category = $('#category').val();
     	
     	const price = $('#price').val();
     	
     	let check = /^[0-9]+$/; 
     	
        if(title === '' || title.trim() === '') {
			alert('제목을 입력해주세요.');
            $('#title').focus();
            return false;
		}else if(content === '' || content.trim() === '') {
            alert('내용을 입력해주세요.');
            $('#content').focus();
            return false;
        }else if(category === '카테고리를 선택해주세요.') {
			alert('카테고리를 선택해주세요.');
            $('#category').focus();
            return false;
		}else if(price === '' || price.trim() === '') {
			alert('가격을 입력해주세요');
            $('#price').focus();
            return false;
		}else if(!check.test(price)) {
			alert('가격은 숫자만 입력 가능합니다.')
			$('#price').focus();
            return false;
		}
	
		for (var i = 0; i < images.length; i++) {
			$.ajax({
				type : 'POST',
				url : '../item/deleteFilePro.jsp',
				dataType: 'json',
				data: "file_id="+images[i]
			}).done(function (){
				return true;
	        }).fail(function (error){
				return false;
	        });
        }
	}
}
    

editForm.init();

