// React
import React from 'react';

// RTL
import { render } from '@testing-library/react';

// Components
import BaseComponent from './BaseComponent';

const renderComponent = () => render(<BaseComponent />);

describe('Head', () => {
  test('Should renders the component', () => {
    const { container } = renderComponent();
    expect(container).toBeDefined();
  });
});
