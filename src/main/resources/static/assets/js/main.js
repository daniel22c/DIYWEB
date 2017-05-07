<script>
    $(document).ready(function(){
    // Activate the side menu
    $(".button-collapse").sideNav();
    });
</script>
$(function(){
    $('.todo-item label').click(function(){
        $(this).parent('form').submit();
    });
});