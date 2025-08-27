import React, { useState, useEffect } from 'react';

function Measures() {
    interface Sponsor {
        name: string
    }

    interface Measure {
        number: number,
        title: string,
        origin: string,
        sponsor: Sponsor,
        cosponsors: Sponsor[],
        introducedAt: string,
        source: string,
        subjects: string[]
    }

    const [data, setData] = useState<Measure[]>([])

    useEffect(() => {
        fetch('http://127.0.0.1:8080/api/measures')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok')
                }
                return response.json();
            })
            .then(data => {
                setData(data);
            })
            .catch(error => {
                console.error('There was a problem fetching from the measures api:', error)
            });
    }, []);


    return (
        <div>
            <ul>
                {data.map((item, index) => (
                    <li key={index}>
                        <div>
                            <h2>{item.origin} {item.number}: {item.title}</h2>
                            <p>{item.sponsor.name}</p>
                            <ul>
                                {item.cosponsors.map((cosponsor, index) => (
                                    <p key={index}>{cosponsor.name}</p>
                                ))}
                            </ul>
                            <p>{item.introducedAt}</p>
                            <p>{item.source}</p>
                            <ul>
                                {item.subjects.map((subject, index) => (
                                    <p key={index}>{subject}</p>
                                ))}
                            </ul>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default Measures