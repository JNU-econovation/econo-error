import { RiDeleteBinLine } from "react-icons/ri";
import axios from "axios";

const DeleteEvent = ({
  events,
  selectID,
  handleUpdateData,
  handleDelete,
  onRequestClose,
}) => {
  const calendarDelete = () => {
    const instance = axios.create({
      baseURL: `${import.meta.env.VITE_ERROR_API}`,
      withCredentials: true,
    });
    instance
      .delete("/api/calendar/" + selectID)
      .then(() => {
        const updatedEvents = events.filter(
          (event) => event.id !== parseInt(selectID)
        ); //selectID는 스트링이고 event.id는 숫자형이기 때문에 filter가 안 걸려졌을 것
        handleUpdateData(updatedEvents);
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
        <RiDeleteBinLine size="1.2rem" />
      </button>
    </>
  );
};

export default DeleteEvent;
