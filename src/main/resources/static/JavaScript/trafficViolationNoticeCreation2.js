/**
 * 
 */
'use strict'

// 変更イベントをリッスンする関数
function calculate() {
    // 各input要素の値を取得
    const speed1 = parseFloat($("#speed1").val()) || 0;
    const speed2 = parseFloat($("#speed2").val()) || 0;

    // Thymeleafの変数resultの値を取得
    const result = parseFloat($("#result").val()) || 0;

    // 計算結果を表示する要素を取得
    const resultElement = $("#result");

    // 計算結果を計算
    const calculatedResult = speed2 - speed1;

    // 計算結果を表示
    resultElement.val(`${calculatedResult}`);
}


// 各input要素に変更イベントリスナーを追加
$("#speed1").on("input", calculate);
$("#speed2").on("input", calculate);

// 初回計算を実行
calculate();


$(function() {
	// select1の選択肢をselect2に反映させる
	$("#violation").on('change', function() {
		var selectedValue = $(this).val();
		$("#violationPoints").val(selectedValue);
		$("#fines").val(selectedValue);
		console.log("Violation selected: " + selectedValue);
	});
});
	
$(document).ready(function() {
    var largeTypeSelect = $('#largeTypeSelect');
    var standardSelect = $('#standardSelect');
    var motorcycleSelect = $('#motorcycleSelect');
    var scootersSelect = $('#scootersSelect');
    var hiddenField = $('#violationVehicleId');
    
    
	    // 大型車のセレクトボックスが変更されたときのリスナー
	$('#largeTypeSelect').on('change', function() {
	    if ($(this).val() !== '--大型車--') {
	        // 大型が選択された場合、他の3つのセレクトボックスを無効にする
	        $('#standardSelect').prop('disabled', true);
	        $('#motorcycleSelect').prop('disabled', true);
	        $('#scootersSelect').prop('disabled', true);
	        $('#violationVehicleId').val($(this).val());
	    } else {
	        // 大型が選択解除された場合、他の3つのセレクトボックスを有効にする
	        $('#standardSelect').prop('disabled', false);
	        $('#motorcycleSelect').prop('disabled', false);
	        $('#scootersSelect').prop('disabled', false);
	        $('#violationVehicleId').val('');
	    }
	});
	
		
		// 普通車のセレクトボックスが変更されたときのリスナー
	$('#standardSelect').on('change', function() {
	    if ($(this).val() !== '--普通車--') {
	        // 普通車が選択された場合、他の3つのセレクトボックスを無効にする
	        $('#largeTypeSelect').prop('disabled', true);
	        $('#motorcycleSelect').prop('disabled', true);
	        $('#scootersSelect').prop('disabled', true);
	        $('#violationVehicleId').val($(this).val());
	    } else {
	        // 普通車が選択解除された場合、他の3つのセレクトボックスを有効にする
	        $('#largeTypeSelect').prop('disabled', false);
	        $('#motorcycleSelect').prop('disabled', false);
	        $('#scootersSelect').prop('disabled', false);
	        $('#violationVehicleId').val('');
	    }
	});
	
	
		// 二輪車のセレクトボックスが変更されたときのリスナー
	$('#motorcycleSelect').on('change', function() {
	    if ($(this).val() !== '--二輪車--') {
	        // 二輪車が選択された場合、他の3つのセレクトボックスを無効にする
	        $('#largeTypeSelect').prop('disabled', true);
	        $('#standardSelect').prop('disabled', true);
	        $('#scootersSelect').prop('disabled', true);
	        $('#violationVehicleId').val($(this).val());
	    } else {
	        // 二輪車が選択解除された場合、他の3つのセレクトボックスを有効にする
	        $('#largeTypeSelect').prop('disabled', false);
	        $('#standardSelect').prop('disabled', false);
	        $('#scootersSelect').prop('disabled', false);
	        $('#violationVehicleId').val('');
	    }
	});
	
	
		// 原付のセレクトボックスが変更されたときのリスナー
	$('#scootersSelect').on('change', function() {
	    if ($(this).val() !== '--原付--') {
	        // 原付が選択された場合、他の3つのセレクトボックスを無効にする
	        $('#largeTypeSelect').prop('disabled', true);
	        $('#standardSelect').prop('disabled', true);
	        $('#motorcycleSelect').prop('disabled', true);
	        $('#violationVehicleId').val($(this).val());
	    } else {
	        // 原付が選択解除された場合、他の3つのセレクトボックスを有効にする
	        $('#largeTypeSelect').prop('disabled', false);
	        $('#standardSelect').prop('disabled', false);
	        $('#motorcycleSelect').prop('disabled', false);
	        $('#violationVehicleId').val('');
	    }
	});
});


$(document).ready(function() {
  $("#birthday").change(function() {
    // 生年月日から年齢を計算
    var birthday = new Date($(this).val());
    var today = new Date();
    var age = today.getFullYear() - birthday.getFullYear();

    // 誕生日がまだ来ていない場合は1歳引く
    if (today.getMonth() < birthday.getMonth() || (today.getMonth() === birthday.getMonth() && today.getDate() < birthday.getDate())) {
      age--;
    }

    // 年齢を年齢フィールドにセット
    $("#age").val(age);
  });

  // フォームロード時にchangeイベントを発火
  $("#birthday").trigger("change");
});

/*$(document).ready(function() {
	$("#birthday").change(function() {
		// 生年月日から年齢を計算
		var birthday = new Date($(this).val());
		var today = new Date();
		var age = today.getFullYear() - birthday.getFullYear();

		// 誕生日がまだ来ていない場合は1歳引く
		if (today.getMonth() < birthday.getMonth() || (today.getMonth() === birthday.getMonth() && today.getDate() < birthday.getDate())) {
			age--;
		}

		// 年齢を年齢フィールドにセット
		$("#age").val(age);
	});
});
*/

$(document).ready(function() {
	// 生年月日から年齢を計算する関数
	function calculateAge(birthday) {
		var today = new Date();
		var age = today.getFullYear() - birthday.getFullYear();

		if (today.getMonth() < birthday.getMonth() || (today.getMonth() === birthday.getMonth() && today.getDate() < birthday.getDate())) {
			age--;
		}

		return age;
	}

	// 交付日から5年後の誕生月を計算する関数
	function calculateExpiryDate(birthday, issuanceDate) {
		var fiveYearsLater = new Date(issuanceDate);
		fiveYearsLater.setFullYear(fiveYearsLater.getFullYear() + 5);

		// 生年月日の日付部分を取得
		var birthDate = new Date(birthday.getFullYear(), birthday.getMonth(), birthday.getDate());

		// 誕生日が交付日より後かどうかを確認
		if (birthDate <= issuanceDate) {
			return new Date(fiveYearsLater.getFullYear(), birthday.getMonth() + 1, birthday.getDate() + 1).toISOString().slice(0, 10);
		} else {
			// 交付日が誕生日より前の場合、5年後の誕生月は誕生日の翌月となる
			return new Date(birthday.getFullYear() + 5, birthday.getMonth() + 1, birthday.getDate() + 1).toISOString().slice(0, 10);
		}
	}

	// 生年月日が変更されたときに実行される関数
	$("#birthday").change(function() {
		var birthday = new Date($(this).val());
		var age = calculateAge(birthday);

		// 年齢を年齢フィールドにセット
		$("#age").val(age);
	});

	// 交付日が変更されたときに実行される関数
	$("#issueDate").change(function() {
		var issuanceDate = new Date($(this).val());
		var birthday = new Date($("#birthday").val());

		// 有効期限を計算して有効期限フィールドにセット
		var expiryDate = calculateExpiryDate(birthday, issuanceDate);
		$("#expiryDate").val(expiryDate);
	});
});


// サーバーサイドで得られたメッセージ
    var speedMsg = document.querySelector('span').innerText.trim();

    // メッセージがnullでない場合に表示
    if (speedMsg !== "") {
        // メッセージを表示するために適切なDOM要素に設定
        document.querySelector('span').innerText = speedMsg;
    } else {
        // メッセージがnullの場合、DOM要素を非表示にするなどの処理を追加
        // 例: document.querySelector('span').style.display = 'none';
        document.querySelector('span').style.display = 'none';
     }
