import { RiDeleteBinLine } from "react-icons/ri";
import axios from "axios";

const DeletEvent = ({ events, setEvents, selectID }) => {
  const calendarDelete = () => {
    const instance = axios.create({
      baseURL: `${import.meta.env.VITE_ERROR_API}`,
    });
    instance
      .delete("/api/calendar/" + selectID)
      .then(() => {
        const updatedEvents = events.filter((event) => event.id !== selectID);
        setEvents(updatedEvents);
        window.location.reload();
      })
      .catch((error) => {
        console.error("Error deleting event:", error);
      });
  };

  return (
    <button onClick={calendarDelete}>
      <RiDeleteBinLine size="1.2rem" />
    </button>
  );
};

export default DeletEvent;
