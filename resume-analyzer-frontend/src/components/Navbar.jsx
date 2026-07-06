function Navbar() {
  return (
    <nav className="bg-white shadow-sm border-b border-gray-200">

      <div className="max-w-7xl mx-auto px-8 py-5 flex justify-between items-center">

        <div>

          <h1 className="text-3xl font-bold text-blue-600">
            🤖 AI Resume Analyzer
          </h1>

          <p className="text-gray-500 text-sm mt-1">
            AI Powered ATS Resume Analysis
          </p>

        </div>

        <div className="flex gap-4">

          <button className="px-5 py-2 rounded-lg bg-gray-100 font-semibold">

            Dashboard

          </button>

          <button className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-2 rounded-lg transition">

            History

          </button>

        </div>

      </div>

    </nav>
  );
}

export default Navbar;