import React, { useState } from "react";
import Select from "react-select";

// 시간 option 만들기 - 시(hour)
let hour = [];
for (let i = 0; i < 24; i++) {
  // 24시간을 30분 간격으로 나누는 것으로 수정
  // 48개의 시간 슬롯(30분 간격) 생성
  let op = {};
  let hourPart = i / 2; // 시간 부분 (1시간 단위로 증가)
  let minutePart = i % 2 === 0 ? "00" : "30"; // 분 부분 (0분 또는 30분)

  // 시간을 HH:MM 형식으로 나타내기 위해
  op.value = ("0" + Math.floor(hourPart)).slice(-2) + ":" + minutePart; // Math.floor 추가
  op.label = ("0" + Math.floor(hourPart)).slice(-2) + "시 " + minutePart + "분"; // Math.floor 추가

  hour.push(op);
}

const TimeSelect = ({ onTimeSelect }) => {
  // 선택된 시간을 컴포넌트 내부에서도 관리하기 위해 useState 사용
  const [timeValue, setTimeValue] = useState("");

  const handleChange = (option) => {
    setTimeValue(option.value); // 상태 업데이트
    // 상위 컴포넌트로 선택된 시간 전달
    if (onTimeSelect) {
      onTimeSelect(option.value);
    }
  };

  return (
    <>
      <Select
        value={hour.find((option) => option.value === timeValue)} // 현재 선택된 시간을 표시
        onChange={handleChange}
        placeholder="00시 00분"
        options={hour}
      />
    </>
  );
};

export default TimeSelect;
