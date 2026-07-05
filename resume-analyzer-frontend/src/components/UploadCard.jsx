function UploadCard() {

    return (

        <div className="bg-white rounded-xl shadow-md p-8 text-center">

            <h2 className="text-3xl font-bold">

                Upload Resume

            </h2>

            <p className="text-gray-500 mt-3">

                Upload your resume and receive AI-powered ATS analysis.

            </p>

            <button
                className="mt-8 bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-lg">

                Choose Resume

            </button>

        </div>

    );

}

export default UploadCard;