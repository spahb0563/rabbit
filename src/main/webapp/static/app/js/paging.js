const paiging = {    
	
    init: function () {
    const _this = this;

    $('.page-link').on('click', function () {
        _this.movePage($(this).val());
	    })
    },
    
    movePage : function(pageNum) {
        const URLSearch = new URLSearchParams(location.search);
        URLSearch.set('pageNum', pageNum);
        const newParam = decodeURIComponent(URLSearch.toString());
        window.open(location.pathname + '?' + newParam, '_self');
    }
}

paiging.init();