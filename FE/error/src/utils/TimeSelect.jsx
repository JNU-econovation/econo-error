import { useState } from "react";
import Select from "react-select";

let hour = [];
for (let i = 0; i < 24; i++) {
  let op = {};
  let hourPart = i / 2;
  let minutePart = i % 2 === 0 ? "00" : "30";

  op.value = ("0" + Math.floor(hourPart)).slice(-2) + ":" + minutePart;
  op.label = ("0" + Math.floor(hourPart)).slice(-2) + "시 " + minutePart + "분";

  hour.push(op);
}

const TimeSelect = ({ onTimeSelect }) => {
  const [timeValue, setTimeValue] = useState("");
  const handleChange = (option) => {
    setTimeValue(option.value);
    if (onTimeSelect) {
      onTimeSelect(option.value);
    }
  };

  return (
    <>
      <Select
        value={hour.find((option) => option.value === timeValue)}
        onChange={handleChange}
        placeholder="00시 00분"
        options={hour}
      />
    </>
  );
};

export default TimeSelect;
