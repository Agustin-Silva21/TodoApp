    <%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
    <div class="container">
            <h1>Todo details</h1>
            <%--@elvariable id="todo" type="ch"--%>
            <form:form method="post" modelAttribute="todo">

                <fieldset class="mb-3">
                    <form:label path="description">Description</form:label>
                    <form:input type="text" path="description" required="required"/>
                    <form:errors path="description" cssClass="error"/>
                </fieldset>

                <fieldset class="mb-3">
                    <form:label path="targetDate">Target Date</form:label>
                    <form:input type="text" path="targetDate" required="required"/>
                    <form:errors path="targetDate" cssClass="error"/>
                </fieldset>

                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="isDone"/>
                <input type="submit" value="Save"/>
            </form:form>
    </div>
    <%@ include file="common/footer.jspf" %>
    <script>
        $(document).ready(function () {
            $('#targetDate').datepicker({
                format: 'dd/mm/yyyy'
            });
        });
    </script>