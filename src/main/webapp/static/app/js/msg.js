const msg = {
    init: function () {
        const _this = this;
        $('#btn-writeMsg').on('click', function () {
            _this.wrtie();
        }),
        
        $('.modal').on('hidden.bs.modal', function (e) {
			$(this).find('form')[0].reset();
		})
		
		$('button.write_review').on('click', function(e) {
			_this.change(e.target.value);
		})
		
		$('#btn-writeReview').on('click', function() {
			_this.writeReview();
		})
		
		$('#selectContent').on('change', function() {
			_this.selectContent();
		})
		
    },

    wrtie : function () {

		const sender_id = $('#sender_id').val();
		
        const receiver_id = $('#receiver_id').val();
        
        const content = $('#content').val().replace(/\n/g, "<br/>");
		
		const item_id = $('#item_id').val(); 
		
        $.ajax({
            type : 'POST',
            url : '../msg/writePro.jsp',
            dataType: 'json',
            data: "sender_id="+sender_id+"&receiver_id="+receiver_id+"&content="+content+"&item_id="+item_id
        }).done(function (){
            alert('전송 완료');
			$('#msg').modal("hide");
        }).fail(function (error){
            alert('전송 실패');
        });
    },
    
    change : function(target_id) {
		$('#target_id').val(target_id);
	},
    
    writeReview : function() {
		const target_id = $('#target_id').val();
	
		const writer_id = $('#writer_id').val();
		
		const content = $('#content').val();
		
		$.ajax({
            type : 'POST',
            url : '../review/insertReviewPro.jsp',
            dataType: 'json',
            data: "target_id="+target_id+"&writer_id="+writer_id+"&content="+content
        }).done(function (){
            alert('작성 완료');
			$('#review').modal("hide");
        }).fail(function (error){
            alert('작성 실패');
        });
	},
	
	selectContent : function() {
		$('#content').val($('#selectContent').val());
	}
};

msg.init();

