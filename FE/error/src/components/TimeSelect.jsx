import React, { useState } from "react";
import Select from "react-select";

// 시간 option 만들기 - 시(hour)
let hour = [];
for (let i = 0; i < 48; i++) {
  // 48개의 시간 슬롯(30분 간격) 생성
  let op = {};
  let hourPart = Math.floor(i / 2); // 시간 부분 (1시간 단위로 증가)
  let minutePart = i % 2 === 0 ? "00" : "30"; // 분 부분 (0분 또는 30분)

  // 시간을 HH:MM 형식으로 나타내기 위해
  op.value = ("0" + hourPart).slice(-2) + ":" + minutePart;
  op.label = ("0" + hourPart).slice(-2) + "시 " + minutePart + "분";

  hour.push(op);
}

const TimeSelect = () => {
  const [timeValue, setTimeValue] = useState("");

  return (
    <>
      <Select
        onChange={(option) => setTimeValue(option.value)} // 수정된 부분
        placeholder="00시 00분"
        options={hour}
      />
    </>
  );
};

export default TimeSelect;
