import Navbar from "../components/Navbar";
import UploadCard from "../components/UploadCard";
import ATSCard from "../components/ATSCard";
import SkillsCard from "../components/SkillsCard";
import SummaryCard from "../components/SummaryCard";
import SuggestionsCard from "../components/SuggestionsCard";
import ProjectReviewCard from "../components/ProjectReviewCard";
import CareerCard from "../components/CareerCard";

function Dashboard() {
  return (
    <div className="min-h-screen bg-slate-100">

      <Navbar />

     <div className="max-w-6xl mx-auto p-8 space-y-8">

        <UploadCard />

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">

          <ATSCard />

          <SkillsCard />

        </div>

        <SummaryCard />

        <SuggestionsCard />

        <ProjectReviewCard />

        <CareerCard />

      </div>

    </div>
  );
}

export default Dashboard;