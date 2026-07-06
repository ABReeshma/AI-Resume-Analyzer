function SkillsCard() {

    const skills = [

        "Java",
        "Spring Boot",
        "React",
        "JWT",
        "MySQL",
        "Git",
        "REST API"

    ];

    return (

        <div className="bg-white rounded-2xl shadow-md p-8">

            <h2 className="text-xl font-bold mb-6">

                Detected Skills

            </h2>

            <div className="bg-blue-100 text-blue-700 px-4 py-2 rounded-full font-medium hover:bg-blue-600 hover:text-white transition">

                {skills.map((skill) => (

                    <span
                        key={skill}
                        className="bg-blue-100 text-blue-700 px-4 py-2 rounded-full font-medium">

                        {skill}

                    </span>

                ))}

            </div>

        </div>

    );

}

export default SkillsCard;