/**
 * 
 */
/**違反歴検索フォームでリセットボタンをクリックすると、違反項目だけを初期値に戻す処理 */
$(function(){
    $('#resetButton').on('click', function(){
        $('#violation').prop("selectedIndex",0).trigger("change");
    });
});
