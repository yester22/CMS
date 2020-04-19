/**
 * jsgrid Utility
 */
var jsGridUtil = {
	
	init : function() {
		this.selectedItems = [];
	},
	
	getSelectedItem  : function () {
		return jsGridUtil.selectedItems;
	},
	
	checkBoxHeader  : function ( callbackFunction ) {
		return $('<input>').attr('type', 'checkbox').attr('name', 'checkAllItem').on('change', callbackFunction);
	},
	
	checkBoxItem  : function( _, item ) {
		
		if (this.selectedItems == null ) jsGridUtil.init();
		
		return $("<input>").attr("type", "checkbox").prop("checked", $.inArray(item, jsGridUtil.selectedItems) > -1)
        					.on("change", function () { $(this).is(":checked") ? jsGridUtil.selectItem(item) : jsGridUtil.unselectItem(item); }
        
        );
		
	},
	
	selectItem  : function(item) {
		 jsGridUtil.selectedItems.push(item);
	},
	 
	unselectItem  : function(item) {
		 jsGridUtil.selectedItems = $.grep(selectedItems, function(i) {
	            return i !== item;
		 });
	}
}