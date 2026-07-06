import { FaCloudUploadAlt } from "react-icons/fa";

function UploadCard() {

    return (

        <div className="bg-white rounded-2xl shadow-md p-10 text-center w-full">

            <FaCloudUploadAlt
                className="text-6xl text-blue-600 mx-auto"
            />

            <h2 className="text-3xl font-bold mt-5">

                Upload Your Resume

            </h2>

            <p className="text-gray-500 mt-3">

                Get an ATS score, AI summary, suggestions and career recommendations.

            </p>

            <button
                className="mt-8 bg-blue-600 hover:bg-blue-700 text-white px-10 py-4 rounded-xl font-semibold transition">

                Choose Resume

            </button>

            <p className="text-sm text-gray-400 mt-4">

                Supported formats: PDF • DOC • DOCX

            </p>

        </div>

    );

}

export default UploadCard;