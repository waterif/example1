jQuery(function($) {
	
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('.timepicker').timepicker({
		minuteStep: 1,
		showSeconds: true,
		showMeridian: false,
		defaultTime: false,
		disableFocus: true
	}).parent().next().on(ace.click_event, function(){
		$(this).prev().children().focus();
	});
	
	$('#spinner').ace_spinner({
		value:-1,
		min:-1,
		max:10,
		step:1, 
		on_sides: true, 
		icon_up:'icon-plus smaller-75', 
		icon_down:'icon-minus smaller-75', 
		btn_up_class:'btn-success' , 
		btn_down_class:'btn-danger'
	});
	
	$('textarea[class*=autosize]').autosize({append: "\n"});
	
	$('#table-1 td .btn').on('click' , function(e){
		var that = this;
		var id = $(this).closest('tr').data('id');
		bootbox.dialog({
			message: "您确认要重新同步吗？",
			buttons: 			
			{
				"yes" :
				{
					"label" : "是",
					"className" : "btn-sm btn-primary",
					"callback": function() {
						$.post(ctx + '/admin/plugins/yunwen/refresh', {id: id}, function(data){
							if (data && data.retCode == 0) {
								$.gritter.add({
									title: 'Success',
									text: 'id:' + id + ', refresh success.<br/>' + (data.retObj ? data.retObj : ''),
									time: 3000,
									class_name: 'gritter-success gritter-light'
								});
							} else {
								$.gritter.add({
									title: 'Failed',
									text: 'id:' + id + ', refresh failed.<br/>' + (data.retObj ? data.retObj : ''),
									time: 3000,
									class_name: 'gritter-danger gritter-light'
								});
							}
						});
					}
				}, 
				"click" :
				{
					"label" : "否",
					"className" : "btn-sm btn-info",
				}
			}
		});
	});
	
	$('body #userpush-btn').on('click' , function(e){
		var users = $('#userpush').val();
		
		if (users && users != '') {
			$.ajax({
				type: "POST",
				url: ctx + "/yunwen/users",
				contentType: "application/json;",
				data: users,
				success: function (data) {
					$.gritter.add({
						title: 'Result',
						text: (data.retObj ? data.retObj : 'No message.'),
						time: 3000,
						class_name: 'gritter-info gritter-light'
					});
				},
				error: function (error) {
					$.gritter.add({
						title: 'Error',
						text: 'Request error.',
						time: 3000,
						class_name: 'gritter-danger gritter-light'
					});
				}
			});
		}
	});
	
	$('body #sitepush-btn').on('click' , function(e){
		var sites = $('#sitepush').val();
		if (sites && sites != '') {
			$.ajax({
				type: "POST",
				url: ctx + "/yunwen/sites",
				contentType: "application/json;",
				data: sites,
				success: function (data) {
					$.gritter.add({
						title: 'Result',
						text: (data.retObj ? data.retObj : 'No message.'),
						time: 3000,
						class_name: 'gritter-info gritter-light'
					});
				},
				error: function (error) {
					$.gritter.add({
						title: 'Error',
						text: 'Request error.',
						time: 3000,
						class_name: 'gritter-danger gritter-light'
					});
				}
			});
		}
	});
	
	
	$('body #search').on('click' , function(e){
		var form = buildForm();
		form.submit();
	});
	
	$('body .page').on('click' , function(e){
		var page = $(this).data('page');
		var form = $('#form').get(0);
		form.elements['page'].value = page;
		form.submit();
	});
	
	function buildForm() {
		var crDateStart = $('.search input[name=crDateStart]').val();
		var crTimeStart = $('.searchinput[name=crTimeStart]').val();
		var crDateEnd = $('.search input[name=crDateEnd]').val();
		var crTimeEnd = $('.search input[name=crTimeEnd]').val();
		var upDateStart = $('.search input[name=upDateStart]').val();
		var upTimeStart = $('.search input[name=upTimeStart]').val();
		var upDateEnd = $('.search input[name=upDateEnd]').val();
		var upTimeEnd = $('.search input[name=upTimeEnd]').val();
		
		var displayName = $('.search input[name=displayName]').val();
		var userId = $('.search input[name=userId]').val();
		var siteId = $('.search input[name=siteId]').val();
		var status = $('.search input[name=status].ace.ace-switch:checked').val() ? 1 : 0;
		var times = $('.search input[name=times]').val();
		
		var form = $('#form').get(0);
		
		if (crDateStart && crTimeStart) {
			form.elements['createTimeStart'].value = crDateStart + ' ' + crTimeStart;
		} else if (crDateStart) {
			form.elements['createTimeStart'].value = crDateStart + ' 00:00:00';
		}
		if (crDateEnd && crTimeEnd) {
			form.elements['createTimeEnd'].value = crDateEnd + ' ' + crTimeEnd;
		} else if (crDateEnd) {
			form.elements['createTimeEnd'].value = crDateEnd + ' 00:00:00';
		}
		if (upDateStart && upTimeStart) {
			form.elements['updateTimeStart'].value = upDateStart + ' ' + upTimeStart;
		} else if (upDateStart) {
			form.elements['updateTimeStart'].value = upDateStart + ' 00:00:00';
		}
		if (upDateEnd && upTimeEnd) {
			form.elements['updateTimeEnd'].value = upDateEnd + ' ' + upTimeEnd;
		} else if (upDateEnd) {
			form.elements['updateTimeEnd'].value = upDateEnd + ' 00:00:00';
		}
		form.elements['displayName'].value = displayName;
		form.elements['userId'].value = userId;
		form.elements['siteId'].value = siteId;
		form.elements['status'].value = status;
		form.elements['page'].value = 1;
		form.elements['times'].value = times;
		
		return form;
	}
	
	
	
	
	
	
	
	
	
	
	
	
})