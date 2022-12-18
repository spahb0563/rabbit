const detail = {
    init: function () {
        const _this = this;
        $('#btn-wish').on('click', function () {
			_this.updateLike();
        })
        
        $('#btn-delete').on('click', function () {
			_this.delete();
		})
    },
	
	updateLike : function() {
		
		const fillheart = '<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">' +
        '<path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>' +
        '</svg>';
        
        const heart = '<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">' +
  		'<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>' +
		'</svg>';		
		
		const member_id = $('#member_id').val();
		const item_id = $('#item_id').val();
				
		$.ajax({
			type : 'POST',
			url : '../wishlist/wishListPro.jsp',
			dataType: 'json',
			data: "member_id="+member_id+"&item_id="+item_id
		}).done(function (result){
			if(result.like == '0') {
				$('#btn-wish').html(heart);
			}else if(result.like == '1'){
				$('#btn-wish').html(fillheart);
			}
        }).fail(function (error){
            alert('좋아요 실패');
        });
	},
	
	delete : function() {
		
		const item_id = $('#item_id').val();
		
		const hostIndex = location.href.indexOf(location.host) + location.host.length;
		
		const path = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));

		if(!confirm('정말 삭제하시겠습니까 ? ')) {
			return false;			
		}		
		
		$.ajax({
			type : 'POST',
			url : '../item/deletePro.jsp',
			dataType: 'json',
			data: "item_id="+item_id
		}).done(function (){
			alert('삭제 성공');
			window.location.href= path+'/main/viewMain.do';
        }).fail(function (error){
            alert('삭제 실패');
        });
	}
};

detail.init();

