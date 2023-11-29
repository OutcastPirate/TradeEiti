import { CourseDto } from "../../models/Course";
import "./Course.scss";

interface Props {
  course: CourseDto;
  handleOnClick?: (course: CourseDto) => void;
}

export function CourseComponent({ course, handleOnClick = () => {} }: Props) {
  return (
    <div className="course" onClick={() => handleOnClick(course)}>
      <h6>{course.course_name}</h6>
      <p>{course.course_id}</p>
    </div>
  );
}
