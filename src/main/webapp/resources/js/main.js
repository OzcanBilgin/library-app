var data="";
var selectedIndex="";

$(function(){

	openAddPopup = function(){
		$('#dialog-form').modal('show');
	}

	openEditPopup = function(i){
		selectedIndex=i;

		editFormBookNameInput.val(data[selectedIndex].bookName);
		editFormAuthorInput.val(data[selectedIndex].author);

		$("#dialog-edit").modal('show');
	};
	openDeletePopup = function(i) {
		selectedIndex=i;
		openConfirmPopup();
	}

	openConfirmPopup = function() {
		$("#dialog-confirm").modal('show');
	}

	/*
	* AJAX
	*
	* */

	refreshTable = function() {

		$.get("/api/v1/library/", function(response, status){
			console.log(response);
			console.log(status);
			data=response.data;
			$('.tr').remove();
			var no = 1;
			for(i=0; i < data.length; i++){
				$("#libraryTable").append('<tr class="tr"> <td>' + no + '</td> <td>' + data[i].id + '</td> <td>' + data[i].bookName + '</td> <td>' + data[i].author + '</td> <td>  <button class="btn btn-warning" onclick="openEditPopup('+i+')"><i class="fa fa-pencil"></i></button></td> <td> <button class="btn btn-danger" onclick="openDeletePopup('+i+');"><i class="fa fa-trash"></i></button> </td> </tr>');
				no=no+1;
			}
		});

	}

	delete_ = function(selectedIndex) {
		$.ajax({
			url: "/api/v1/library/" + data[selectedIndex].id ,
			type:"DELETE",
			dataType: 'json',
			success: function(response){
				refreshTable();
			}
		});


	}

	add_ = function(bookName, author) {

		$.post({
			url: "/api/v1/library/",
			dataType: 'json',
			data: JSON.stringify({id: null,bookName: bookName,
				author: author}),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			contentType: 'application/json',
			success: function(response){
				refreshTable();
			}
		});
	}

	edit_ = function(selectedId, bookName, author) {
		$.ajax({
			url: "/api/v1/library/" + selectedId,
			type:"PUT",
			dataType: 'json',
			data: JSON.stringify({id: selectedId,
				bookName: bookName,
				author: author}),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			contentType: 'application/json',
			success: function(response){
				refreshTable();
			}
		});
	}

	/*
	*
	* ADD
	*
	* */

	var saveFormBookNameInput = $( "#saveFormBookNameInput" );
	var saveFormAuthorInput = $( "#saveFormAuthorInput" );
	var allFields = $( [] ).add( saveFormBookNameInput ).add( saveFormAuthorInput );
	var tips = $( ".validateTips" );

	onClickedSave = function () {
		bookName = saveFormBookNameInput.val();
		author = saveFormAuthorInput.val();

		var valid = true;
		allFields.removeClass( "ui-state-error" );

		valid = valid && saveFormValid( saveFormBookNameInput, "Book Name", 0);
		valid = valid && saveFormValid( saveFormAuthorInput, "Author", 0);

		if ( valid ) {
			$('#dialog-form').modal('hide');
			add_(bookName,author);
		}
	}

	function updateFormTips( t ) {
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}

	function saveFormValid(o, n, min) {
		if (o.val().length == min) {
			o.addClass( "ui-state-error" );
			updateFormTips( "" + n + " can not be empty.")
			return false;
		} else {
			return true;
		}
	}

	/*
	*
	* EDIT
	*
	* */

	var editFormBookNameInput = $( "#editFormBookNameInput" );
	var editFormAuthorInput = $( "#editFormAuthorInput" );
	var editFormAllFields = $( [] ).add( editFormBookNameInput ).add( editFormAuthorInput );
	var editFormTips = $( ".editValidateTips" );

	onClickedEdit = function () {
		bookName = editFormBookNameInput.val();
		author = editFormAuthorInput.val();
		var selectedId = data[selectedIndex].id;
		var valid = true;
		editFormAllFields.removeClass( "ui-state-error" );

		valid = valid && checkLength( editFormBookNameInput, "Book Name", 0);
		valid = valid && checkLength( editFormAuthorInput, "Author", 0);

		if ( valid ) {
			$('#dialog-edit').modal('hide');
			edit_(selectedId, bookName, author);
		}
	}

	function updateTips( t ) {
		editFormTips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			editFormTips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}

	function checkLength(o, n, min) {
		if (o.val().length == min) {
			o.addClass( "ui-state-error" );
			updateTips( "" + n + " can not be empty.")
			return false;
		} else {
			return true;
		}
	}
	/*
	* DELETE
	*
	* */
	onClickedDelete = function () {
		delete_(selectedIndex);
		$("#dialog-confirm").modal('hide');

	}


});

