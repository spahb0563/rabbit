const address = {
    init: function () {
        const _this = this;
        $('#city').on('change', function () {
			_this.searchCity();
        })
        
        $('#district').on('change', function () {
			_this.searchDistrict();
		})
    },
	
	searchCity : function() {
		if($('#city').val() == '지역을 선택해주세요.') {
			$('#city').focus();
			return
		}
		$('#cityForm').submit();
	},
	
	searchDistrict : function () {
		if($('#district').val() == '지역을 선택해주세요.') {
			$('#district').focus();
			return
		}
		$('#districtForm').submit();
	}
};

address.init();

