import { RiDeleteBinLine } from "react-icons/ri";
import axios from "axios";

const DeleteEvent = ({ events, selectID, handleDelete, onRequestClose }) => {
  const calendarDelete = () => {
    const instance = axios.create({
      baseURL: `${import.meta.env.VITE_ERROR_API}`,
      withCredentials: true,
    });
    instance
      .delete("/api/calendar/" + selectID)
      .then(() => {
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
