/**
 * jsgrid Utility
 */
class jsGridUtil {
	
	static init() {
		this.selectedItems = [];
	}
	
	static getSelectedItem() {
		return jsGridUtil.selectedItems;
	}
	
	static checkBoxHeader ( callbackFunction ) {
		return $('<input>').attr('type', 'checkbox').attr('name', 'checkAllItem').on('change', callbackFunction);
	}
	
	static checkBoxItem( _, item ) {
		
		if (this.selectedItems == null ) jsGridUtil.init();
		
		return $("<input>").attr("type", "checkbox").prop("checked", $.inArray(item, jsGridUtil.selectedItems) > -1)
        					.on("change", function () { $(this).is(":checked") ? jsGridUtil.selectItem(item) : jsGridUtil.unselectItem(item); }
        
        );
		
	}
	
	static selectItem (item) {
		 jsGridUtil.selectedItems.push(item);
	};
	 
	static unselectItem(item) {
		 jsGridUtil.selectedItems = $.grep(selectedItems, function(i) {
	            return i !== item;
		 });
	};
}