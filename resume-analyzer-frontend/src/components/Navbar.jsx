function Navbar() {
  return (
    <nav className="bg-white shadow-md">

      <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">

        <h1 className="text-3xl font-bold text-blue-600">

          🤖 AI Resume Analyzer

        </h1>

        <button
          className="bg-blue-600 text-white px-5 py-2 rounded-lg hover:bg-blue-700 transition">

          History

        </button>

      </div>

    </nav>
  );
}

export default Navbar;