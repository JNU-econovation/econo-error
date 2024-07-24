import { RiDeleteBinLine } from "react-icons/ri";
import axios from "axios";

const DeleteEvent = ({
  selectID,
  handleDelete,
  onRequestClose,
  handleUpdateDeleteData,
}) => {
  const storedToken = localStorage.getItem("slackToken");

  const calendarDelete = () => {
    axios
      .delete(`/api/calendar/${selectID}`, {
        headers: {
          Authorization: `Bearer ${storedToken}`,
        },
      })
      .then(() => {
        handleUpdateDeleteData(selectID);
        handleDelete();
        onRequestClose();
      })
      .catch((error) => {
        console.error("Error deleting event:", error);
      });
  };

  return (
    <>
      <button onClick={calendarDelete}>
        <RiDeleteBinLine size="1.6rem" color="rgb(95, 99, 104)" />
      </button>
    </>
  );
};

export default DeleteEvent;
