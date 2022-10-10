<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Library</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body id="body" onload="refreshTable();">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>Library List</h1>
            </div>
            <div class="col">
                <div class="float-end mt-2">
                    <button type="button" class="btn btn-primary" onclick="openAddPopup();"><i class="fa fa-plus"></i></button>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <table class="table " id="libraryTable">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Book Name</th>
                        <th scope="col">Author</th>
                        <th colspan="2">Options</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

        <!--ADD MODAL -->
        <div  class="modal" id="dialog-form">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add Book</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label">Book Name:</label>
                            <input type="text" name="bookName" class="form-control" id="saveFormBookNameInput" >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Author:</label>
                            <input type="text" name="author" class="form-control" id="saveFormAuthorInput" >
                        </div>
                        <p class="validateTips">All form fields are required.</p>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="onClickedSave();">Save</button>
                    </div>

                </div>
            </div>
        </div>

        <!--EDIT MODAL -->
        <div  class="modal" id="dialog-edit">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Book</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label">Book Name:</label>
                            <input type="text" name="bookName" class="form-control" id="editFormBookNameInput" >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Author:</label>
                            <input type="text" name="author" class="form-control" id="editFormAuthorInput" >
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="onClickedEdit();">Edit</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="modal" tabindex="-1" id="dialog-confirm">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Danger</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>This book will be permanently deleted and cannot be recovered. Are you sure?.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" onclick="onClickedDelete()">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

</body>


</html>