/**
 * User defined JavaScript functions
 */

$(document).ready(function(){
	$('.datepicker').datepicker({
		format: 'dd-mm-yyyy',
		autoclose:true,
		todayHighlight:true
	});
});

$(document).on('change paste', '.inputPrice', function(){
	var sum = 0;
	$('.inputPrice').each(function(){
		sum += +$(this).val();
	});
	$('#amount').val(sum);
});