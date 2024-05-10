//document.addEventListener('DOMContentLoaded', () => {
//    const seats = document.querySelectorAll('.seat');
//
//    seats.forEach(seat => {
//        seat.addEventListener('click', () => {
//            if (!seat.classList.contains('selectedSeatIcon')) {
//                seat.classList.add('selectedSeatIcon');
//                seat.classList.remove('seat');
//                reserveSeat(seat)
//            } else {
//                seat.classList.remove('selectedSeatIcon');
//                seat.classList.add('seat');
//                cancelReservation(seat);
//            }
//        });
//    });
//    });
//
//let selectedSeats = [];
//
//function reserveSeat(seatElement) {
//const row = seatElement.getAttribute('data-row');
//const column = seatElement.getAttribute('data-column');
//const seatInfo = { row: row, column: column };
//
//selectedSeats.push(seatInfo);
//}
//
//function cancelReservation(seatElement) {
//const row = seatElement.getAttribute('data-row');
//const column = seatElement.getAttribute('data-column');
//const index = selectedSeats.findIndex(seat => seat.row === row && seat.column === column);
//
//    if (index !== -1) {
//    selectedSeats.splice(index, 1);
//    }
//}
//
//function submitSelectedSeats() {
//    // 선택된 좌석 정보를 JSON 문자열로 변환
//    const selectedSeatsJson = JSON.stringify(selectedSeats);
//
//    // hidden input에 선택된 좌석 정보 설정
//    const selectedSeatsInput = document.getElementById('selectedSeatsInput');
//    selectedSeatsInput.value = selectedSeatsJson;
//
//    // movieNo, locationNo, date 값을 가져와 hidden input에 설정
//    const movieNo = document.getElementById('movieNo').value;
//    const locationNo = document.getElementById('locationNo').value;
//    const date = document.getElementById('date').value;
//
//    // 각각의 hidden input에 값 설정
//    document.getElementById('movieNoInput').value = movieNo;
//    document.getElementById('locationNoInput').value = locationNo;
//    document.getElementById('dateInput').value = date;
//
//    // 폼 제출
//    document.getElementById("seatForm").submit();
//}

