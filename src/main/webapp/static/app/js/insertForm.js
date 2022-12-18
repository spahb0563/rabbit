const insertForm = {
    init: function () {
        const _this = this;
    },

    check : function () {
     	
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
		}else {
			return true;
		}
    }
};

insertForm.init();

