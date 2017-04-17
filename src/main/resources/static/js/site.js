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

$(document).on('change','.inputPrice', function(){
	var value=$(".inputPrice").val();
	var name=[10];
	name.push(value);
	
	console.log("Price is "+value);
	console.log(name.length);
});