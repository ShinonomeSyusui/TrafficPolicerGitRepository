/**
 * 
 */
'use strict'
// 変更イベントをリッスンする関数
function calculate() {
	// 各input要素の値を取得
	const speed1 = parseFloat(document.getElementById("speed1").value) || 0;
	const speed2 = parseFloat(document.getElementById("speed2").value) || 0;

	// Thymeleafの変数resultの値を取得
	const result = parseFloat(document.getElementById("result").value) || 0;

	// 計算結果を表示する要素を取得
	const resultElement = document.getElementById("result");

	// 計算結果を計算
	const calculatedResult = speed2 - speed1;

	// 計算結果を表示
	resultElement.value = `${calculatedResult}`;
}

// 各input要素に変更イベントリスナーを追加
document.getElementById("speed1").addEventListener("input", calculate);
document.getElementById("speed2").addEventListener("input", calculate);

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





document.addEventListener('DOMContentLoaded', function() {
	var largeTypeSelect = document.querySelector('#largeTypeSelect');
	var standardSelect = document.querySelector('#standardSelect');
	var motorcycleSelect = document.querySelector('#motorcycleSelect');
	var scootersSelect = document.querySelector('#scootersSelect');
	var hiddenField = document.querySelector('#violationVehicleId');

	// 大型車のセレクトボックスが変更されたときのリスナー
	largeTypeSelect.addEventListener('change', function() {
		if (largeTypeSelect.value !== '--大型車--') {
			// 大型が選択された場合、他の3つのセレクトボックスを無効にする
			standardSelect.disabled = true;
			motorcycleSelect.disabled = true;
			scootersSelect.disabled = true;
			hiddenField.value = largeTypeSelect.value;
		} else {
			// 大型が選択解除された場合、他の3つのセレクトボックスを有効にする
			standardSelect.disabled = false;
			motorcycleSelect.disabled = false;
			scootersSelect.disabled = false;
			hiddenField.value = '';
		}
	});

	// 普通車のセレクトボックスが変更されたときのリスナー
	standardSelect.addEventListener('change', function() {
		if (standardSelect.value !== '--普通車--') {
			// 普通車が選択された場合、他の3つのセレクトボックスを無効にする
			largeTypeSelect.disabled = true;
			motorcycleSelect.disabled = true;
			scootersSelect.disabled = true;
			hiddenField.value = standardSelect.value;
		} else {
			// 普通車が選択解除された場合、他の3つのセレクトボックスを有効にする
			largeTypeSelect.disabled = false;
			motorcycleSelect.disabled = false;
			scootersSelect.disabled = false;
			hiddenField.value = '';
		}
	});

	// 二輪車のセレクトボックスが変更されたときのリスナー
	motorcycleSelect.addEventListener('change', function() {
		if (motorcycleSelect.value !== '--二輪車--') {
			// 二輪車が選択された場合、他の3つのセレクトボックスを無効にする
			largeTypeSelect.disabled = true;
			standardSelect.disabled = true;
			scootersSelect.disabled = true;
			hiddenField.value = motorcycleSelect.value;
		} else {
			// 二輪車が選択解除された場合、他の3つのセレクトボックスを有効にする
			largeTypeSelect.disabled = false;
			standardSelect.disabled = false;
			scootersSelect.disabled = false;
			hiddenField.value = '';
		}
	});

	// 原付のセレクトボックスが変更されたときのリスナー
	scootersSelect.addEventListener('change', function() {
		if (scootersSelect.value !== '--原付--') {
			// 原付が選択された場合、他の3つのセレクトボックスを無効にする
			largeTypeSelect.disabled = true;
			standardSelect.disabled = true;
			motorcycleSelect.disabled = true;
			hiddenField.value = scootersSelect.value;
		} else {
			// 原付が選択解除された場合、他の3つのセレクトボックスを有効にする
			largeTypeSelect.disabled = false;
			standardSelect.disabled = false;
			motorcycleSelect.disabled = false;
			hiddenField.value = '';
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
